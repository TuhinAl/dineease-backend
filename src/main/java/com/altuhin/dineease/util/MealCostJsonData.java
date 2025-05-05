package com.altuhin.dineease.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class MealCostJsonData implements Serializable {
    List<MealCostData> mealCostDataList;
}
