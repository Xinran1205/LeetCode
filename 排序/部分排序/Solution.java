class Solution {
    public int[] subSort(int[] array) {
        if(array.length==0||array.length==1){
            return new int[]{-1,-1};
        }

        int m = -1;
        int min = array[array.length-1];
        for(int i=array.length-2;i>=0;i--){
            if(array[i]<=min){
                min = array[i];
            }else{
                m = i;
            }
        }
        int n = -1;
        int max = array[0];
        for(int i=1;i<=array.length-1;i++){
            if(array[i]>=max){
                max = array[i];
            }else{
                n = i;
            }
        }
        return new int[]{m,n};
    }
}