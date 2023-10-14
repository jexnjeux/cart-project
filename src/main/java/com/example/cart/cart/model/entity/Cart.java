package com.example.cart.cart.model.entity;

import com.example.cart.member.model.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int count;

  @OneToOne
  @JoinColumn(name = "member_id")
  private Member member;


  public static Cart createCart(Member member) {
    Cart cart = new Cart();
    cart.setCount(0);
    cart.setMember(member);
    return cart;
  }

}
