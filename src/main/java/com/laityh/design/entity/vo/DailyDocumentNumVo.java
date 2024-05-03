package com.laityh.design.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyDocumentNumVo {
    private String date;
    private Integer documentNum;
}
