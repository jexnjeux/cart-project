package com.example.cart.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  USERNAME_NOT_FOUND("존재하지 않는 아이디입니다"),
  USER_ID_NOT_FOUND("존재하지 않는 유저입니다."),
  ALREADY_JOINED_USERNAME("이미 가입된 아이디입니다"),
  MISSING_REQUEST_BODY("필수 입력 사항이 입력되지 않았습니다"),
  NO_MODIFY_PERMISSION("수정 권한이 없습니다."),
  NON_EXISTENT_PRODUCT("상품이 존재하지 않습니다."),
  ALREADY_DELETED_PRODUCT("이미 삭제된 상품입니다."),
  DELETED_PRODUCT("삭제된 상품입니다."),
  CANT_PUT_IN_THE_CART("품절 또는 판매 종료된 상품입니다."),
  EXCESS_MAXIMUM_QUANTITY("장바구니에 담으려는 수량이 재고 수량보다 많습니다."),
  CART_NOT_MATCHED_USER("유저에 해당하는 장바구니가 아닙니다."),
  CART_ITEM_NOT_FOUND("장바구니에 해당 상품이 없습니다.");

  private final String description;
}
