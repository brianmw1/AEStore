package com.a.exception;

public class ItemNotFoundException extends RuntimeException {

  public ItemNotFoundException(String bid) {
    super("Could not find item " + bid);
  }
}
