/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

class VersionControl {
    boolean isBadVersion(int version) {
        // This is a placeholder for the actual implementation.
        return false; // Replace with actual logic.
    }
}

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        int k = 1;
        while(left<=right){
            int mid = (right-left)/2+left;
            if(!isBadVersion(mid)){
                k = mid+1;
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return k;
    }
}