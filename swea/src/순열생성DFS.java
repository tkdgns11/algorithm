package src;

import java.util.Arrays;

public class 순열생성DFS<T> {
    private final T[] elements;      // 원본 원소 배열
    private final boolean[] visited; // 선택한 인덱스 표시
    private final T[] buffer;        // 현재 순열을 저장할 배열
    private final int r;             // 뽑을 개수

    @SuppressWarnings("unchecked")
    public 순열생성DFS(T[] elements, int r) {
        if (r < 0 || r > elements.length) {
            throw new IllegalArgumentException("r must be between 0 and elements.length");
        }
        this.elements = elements;
        this.r = r;
        this.visited = new boolean[elements.length];
        this.buffer = (T[]) new Object[r];
    }

    /** 순열 생성 시작 */
    public void generate() {
        permute(0);
    }

    /** k번째 자리를 채우며 재귀 호출 */
    private void permute(int k) {
        if (k == r) {
            // 완성된 순열 출력
            System.out.println(Arrays.toString(buffer));
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                buffer[k] = elements[i];

                permute(k + 1);

                visited[i] = false;  // 백트래킹
            }
        }
    }

    // 사용 예시
    public static void main(String[] args) {
        Integer[] nums = { 1, 2, 3, 4 };
        int r = 2;

        순열생성DFS<Integer> generator = new 순열생성DFS<>(nums, r);
        generator.generate();
    }
}
