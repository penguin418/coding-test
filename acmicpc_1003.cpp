#include<cstdio>
// [피보나치 함수]
// 메모이제이션을 사용하여 이전 값들로 다음 피보나치수를 구하면 된다
int m[100][2];
void fibonacci(int n) {
	if (m[n][0] < 0){
		fibonacci(n-1);
		m[n][0] = m[n-1][0] + m[n-2][0];
		m[n][1] = m[n-1][1] + m[n-2][1];
	}
}

int main(){
	for (int i=0; i<100; i++)
		m[i][0] = m[i][1] = -1;
	m[0][0] = m[1][1] = 1;
	m[0][1] = m[1][0] = 0;
	int n;
	scanf("%d", &n);
	for (int i=0; i<n; i++){
		int k;
		scanf("%d", &k);
		fibonacci(k);
		printf("%d %d\n", m[k][0], m[k][1]);
	}
}
