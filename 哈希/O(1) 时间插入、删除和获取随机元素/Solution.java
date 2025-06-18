import java.util.*;

class RandomizedSet {
    List<Integer> arr;
    Map<Integer,Integer> hashMap;
    Random random;

    public RandomizedSet() {
        arr = new ArrayList<>();
        hashMap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if(hashMap.containsKey(val)){
            return false;
        }
        int index = arr.size();
        arr.add(val);
        hashMap.put(val, index);
        return true;
    }

    public boolean remove(int val) {
        if(!hashMap.containsKey(val)){
            return false;
        }
        int position = hashMap.get(val);
        int last = arr.get(arr.size() - 1);
        arr.set(position,last);
        arr.remove(arr.size() - 1);
        hashMap.put(last, position);
        hashMap.remove(val);
        return true;
    }

    public int getRandom() {
        int randomVal = random.nextInt(arr.size());
        return arr.get(randomVal);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */