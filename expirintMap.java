public class ExpiringMap<K, V> extends TimerTask {
    // fields
    class Node<K, V> {
        // fields
        private K key;
        private V value;
        private long startTime;

        // methods
        public Node (V value, long duration, long startTime) {
            this.value = value;
            this.duration = duration;
            this.startTime = startTime;
        }
    }

    private LinkedList<Node> list;
    HashMap<K, Node> map;
    public ExpiringMap(){
        map = new HashMap<>();
        list = new LinkedList<>();
    }



    public void put(K key, V value, long duration) {
        long startTime = System.currentTimeMillis();
        Node node = new Node(value, duration, startTime);
        map.put(key, node);
        checkTime();
        addToHead(value, duration, startTime);
    }

    public V get(K key) {
        checkTime();
        if (map.containsKey(key)) {
            Node node = map.get(key);
            return (V) node.value;
        } else {
            return null;
        }
    }

    public void checkTime() {
        long curTime = System.currentTimeMillis();
        while (!list.isEmpty()) {
            Node node = list.getLast();
            if (node.duration + node.startTime > curTime) {
                list.removeLast();
                map.remove(node.key);
            } else {
                break;
            }
        }
    }
    public void addToHead(V value, long duration, long startTime) {
        Node node = new Node(value, duration, startTime);
        list.addFirst(node);
    }

    @Override
    public void run() {
        checkTime();
    }

    public static void main(String[] args) {
        Timer timer = new Timer(true);
        ExpiringMap expiringMap = new ExpiringMap();
        timer.schedule(expiringMap,0,1000);//1000毫秒检查一次
    }
}
