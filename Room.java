class Room {

    Integer count;
    Integer price;
    Set features;

    public Room() {

    }

    public Room(Integer count, Integer price, Set features) {
        this.count = count;
        this.price = price;
        this.features = features;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Room.class.getSimpleName() + "[", "]").add("count=" + count)
            .add("price=" + price).add("features=" + features).toString();
    }
}