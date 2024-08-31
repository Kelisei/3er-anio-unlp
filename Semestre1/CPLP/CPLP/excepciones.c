#include <stdio.h>
int main() {
    float num;
    float[10] array;
    printf("Ingrese un numero \n");
    int i=0;
    while (i < 10) {
        if (scanf("%f", &num) != 1) {
            printf("Error, no es un número");
            return -1;
        } else {
            array[i] = num;
        }
    }
}