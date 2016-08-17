package com.sunny.queue;

/**
 * Created by sunshah on 1/23/16.
 */
public class PacketQueue extends GenericQueue<PacketQueue.Packet> {

    public static class Packet {

        private final int load;
        public Packet(int load) {
            this.load = load;
        }

        public int getLoad() {
            return this.load;
        }

    }

    public PacketQueue(int size) throws IllegalArgumentException {
        super(size);
    }

    public static void main(String[] args) {
        int size = 10;
        PacketQueue packetQueue = new PacketQueue(size);
        packetQueue.size();
        packetQueue.length();
        packetQueue.isEmpty();
        packetQueue.dequeue();
        packetQueue.enqueue(new Packet(4));
        packetQueue.length();

        for(int i = 0; i < 10; i++) {
            boolean enqueueSuccess = packetQueue.enqueue(new Packet(i));
            if(enqueueSuccess) {
                System.out.println("Enqueued packet: " + i);
            }
        }

        for(int i = 0; i < 10; i++) {
            Packet packet = (Packet) packetQueue.dequeue();
            if(packet != null) {
                System.out.println("Dequeued packet load: " + packet.getLoad());
            }
        }
    }
}
