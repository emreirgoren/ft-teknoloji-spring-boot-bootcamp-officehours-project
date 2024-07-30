package com.blogHubAdvisor.bloghub_advisor_service.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriesToVector {



    public double[] categoriesToVector(List<String> categories) {
        double[] vector = new double[10];
        // Kategorileri indekslerle eşleştirin ve vektöre ekleyin
        for (String category : categories) {
            int index = getCategoryIndex(category);
            if (index >= 0 && index < vector.length) {
                vector[index] = 1.0; // One-hot encoding
            }
        }
        return vector;
    }

    private int getCategoryIndex(String category) {
        // Kategori isimlerini indekslerle eşleştirin
        switch (category.toLowerCase()) {
            case "Gezi":
                return 0;
            case "Yemek":
                return 1;
            case "Sağlık":
                return 2;
            // Diğer kategoriler...
            default:
                return -1;
        }
    }


}
