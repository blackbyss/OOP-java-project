package com.ticket.ticketproject.Classes;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
public class Piletikood {
    public static Set<Integer>set = new LinkedHashSet<Integer>();
    public static int piletiteArv = 10;
    public static Set piletiKoodid(Set set) throws Exception {
        Random randNum = new Random();
        while (set.size() < piletiteArv) {
            int id = randNum.nextInt(Math.abs(Integer.MAX_VALUE));
            System.out.println(id);
            set.add(id);
        }
        return set;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(piletiKoodid(set));
        System.out.println(set);
    }
}
