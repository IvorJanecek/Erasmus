package frontend.Erasmus.service;

import frontend.Erasmus.exception.ErasmusException;
import frontend.Erasmus.exception.MobilnostNotFoundException;
import frontend.Erasmus.model.RefreshToken;
import frontend.Erasmus.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    //generiraj refresh token
    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    //Provjeri refresh token
    void validateRefreshToken(String token){
        refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> new ErasmusException("Invalid refresh Token"));
    }

    //obriši refresh token
    public void deleteRefreshToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }
}

