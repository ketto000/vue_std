package org.study.gallery.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class portfolioDto {

    private int idx;

    private String start_date;

    private String deadline;

    private String header;

    private String description;

    private String image_path;
}