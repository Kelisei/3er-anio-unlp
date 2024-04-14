#include <stdio.h>
int main() {
    float radio;
    printf("Ingrese un numero \n");
    if (scanf("%f", &radio) != 1) { 
        printf("Error, no es un numero");
        return -1; 
    }
    printf("El area del circulo es: %.2f\n", 3.14 * radio * radio);
}

#include <stdio.h>
float calcularAreaCirculo(float radio) { return 3.14 * radio * radio; }
int main() {
    float radio;
    if (scanf("%f", &radio) != 1) { return -1; }
    printf("El area del circulo es: %.2f\n", calcularAreaCirculo(radio));
    return 0;
}



