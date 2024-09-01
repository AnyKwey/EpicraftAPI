package eu.epicraft.com.data.utils;

public enum ServerVersion {
  v1_20_1,
  REFLECTED,
  UNKNOWN;

  @Override
  public String toString() {
    return name();
  }
}