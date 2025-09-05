package com.f4stsearch.adapter.out;

public class FakeStoreProductDTO {

    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;

    public FakeStoreProductDTO(Long id, String title, String description, Double price, String category, String image, Rating rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }

    public FakeStoreProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }


    public String getImage() {
        return image;
    }

    public Rating getRating() {
        return rating;
    }

    public static class Rating {
        private Double rate;
        private Integer count;

        public Double getRate() {
            return rate;
        }

        public Integer getCount() {
            return count;
        }

        public Rating(Integer count, Double rate) {
            this.count = count;
            this.rate = rate;
        }

        public Rating() {
        }
    }
}