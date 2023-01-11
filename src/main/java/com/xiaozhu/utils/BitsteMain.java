package com.xiaozhu.utils;

import java.util.BitSet;

public class BitsteMain {
    // 全量bitset
    private static BitSet allBitset = new BitSet();

    // 偶数
    private static BitSet evenBitset = new BitSet();

    // 奇数
    private static BitSet oddBitset = new BitSet();

    // 空bitset
    private static BitSet emptyBitset = new BitSet();

    static void init(){
        for(int i = 0; i < 63; i++) {
            // 将指定索引处的位设置为 true
            allBitset.set(i);
            if(i % 2 == 0) {
                evenBitset.set(i);
            } else {
                oddBitset.set(i);
            }
        }
    }

    public static void main(String[] args) {

    }
}
