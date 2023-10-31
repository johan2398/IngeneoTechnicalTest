/**
 * 
 */
package com.example.ingeneo_technical_test.entity.dto;

/**
 * @author Johan Casagua
 *
 */
public class JwtAuthenticationResponse {
	private String accessToken;
    private String tokenType;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
