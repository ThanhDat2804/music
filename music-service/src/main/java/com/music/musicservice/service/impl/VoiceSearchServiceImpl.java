package com.music.musicservice.service.impl;

import com.music.musicservice.model.projection.SongProjection;
import com.music.musicservice.repository.SongRepository;
import com.music.musicservice.service.VoiceSearchService;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class VoiceSearchServiceImpl implements VoiceSearchService {

    private final SongRepository songRepository;
    private final RestTemplate restTemplate;
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/audio/transcriptions";
    private static final String OPENAI_API_KEY = "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";

    public VoiceSearchServiceImpl(SongRepository songRepository, RestTemplate restTemplate) {
        this.songRepository = songRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<SongProjection> searchSongsByVoice(MultipartFile file) throws IOException {
        // 1️⃣ Gửi file giọng nói đến OpenAI
        HttpEntity<MultiValueMap<String, Object>> requestEntity = createOpenAIRequest(file);
        ResponseEntity<Map> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, requestEntity, Map.class);

        if (response.getBody() == null || !response.getBody().containsKey("text")) {
            throw new RuntimeException("AI processing failed");
        }

        // 2️⃣ Lấy kết quả nhận diện giọng nói
        String query = (String) response.getBody().get("text");

        // 3️⃣ Tìm kiếm bài hát gần đúng
        List<SongProjection> songs = songRepository.searchSongsByQuery(query);
        return findBestMatchingSongs(query, songs);
    }

    // Gửi file âm thanh đến OpenAI Whisper API
    private HttpEntity<MultiValueMap<String, Object>> createOpenAIRequest(MultipartFile file) throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        body.add("model", "whisper-1");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        return new HttpEntity<>(body, headers);
    }

    // Tìm bài hát gần nhất dựa vào Levenshtein Distance
    private List<SongProjection> findBestMatchingSongs(String query, List<SongProjection> songs) {
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        return songs.stream()
                .sorted((s1, s2) -> Integer.compare(
                        levenshtein.apply(query, s1.getName()),
                        levenshtein.apply(query, s2.getName())
                ))
                .limit(5) // Trả về 5 bài hát phù hợp nhất
                .toList();
    }
}


