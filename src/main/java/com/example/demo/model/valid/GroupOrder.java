package com.example.demo.model.valid;

import javax.validation.GroupSequence;

/**
 * バリデーション順番設定
 */
@GroupSequence({ ValidGroup1.class, ValidGroup2.class })
public interface GroupOrder {

}
