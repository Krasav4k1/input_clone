package com.stry4ka.inputClone;

import com.stry4ka.inputClone.client.Client;

public class Launch {
    public static void main(String[] args) {
        Client client = new Client(4565);
        client.listening();
    }
}
