package com.nikhil.elementaldimensions;

import net.fabricmc.api.ClientModInitializer;

/**
 * Client-side initialization for Elemental Dimensions
 * Handles rendering, particles, and client-only features
 */
public class ElementalDimensionsClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        ElementalDimensions.LOGGER.info("Initializing Elemental Dimensions client");
        
        // Client-side initialization
        // Particle and renderer registration can be added here if needed
    }
}
