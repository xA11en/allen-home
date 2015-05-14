package com.tingyun.auto.framework;
/**
* @author :chenjingli 
* @version ：2015-5-12 上午10:34:11 
* @decription:
 */
public class SystemClock {

    public long laterBy(long durationInMillis) {
      return System.currentTimeMillis() + durationInMillis;
    }

    public boolean isNowBefore(long endInMillis) {
      return System.currentTimeMillis() < endInMillis;
    }

    public long now() {
      return System.currentTimeMillis();
    }
}
