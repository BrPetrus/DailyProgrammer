#include <stdio.h>
#include <cmath>

#include <fstream>
#include <iostream>

int main(int argc, char* argv[]) {
    if (argc != 7) {
        std::cerr << "Usage: [Total population] [Infected population] [Rate from susceptible to infected] [Rate from infected to recovered] [Rate from susceptible to recovered] [Number of generations]\n";
        return 1;
    }
    
    std::ofstream file("output");
    int pop = atoi(argv[1]);
    int s, i, r;
    i = atoi(argv[2]);
    s = pop - i;
    r = 0;

    //Rates
    double rStoI = 0.01d, rItoR = 0.01d, rStoR = 0.015d;
    rStoI = atof(argv[3]);
    rItoR = atof(argv[4]);
    rStoR = atof(argv[5]);
        
    printf("%i %i %i %i\n", pop, s, i, r);
    printf("%f %f %f\n", rStoI, rItoR, rStoR);
    
    int gen = 0;
    const int maxGen = atoi(argv[6]);
    while(gen <= maxGen) {
        // Calculate how many susceptible com will be infected
        int addIfS = s * rStoI;
        // Calculate how many susceptible will be patched
        int addRfS = s * rStoR;
        // Calculate how many will be patched (from INFECTED)
        int addRfI = i * rItoR;
        
        s -= (addIfS + addRfS);
        i += addIfS - addRfI;
        r += addRfI + addRfS;
        
        pop = s + i + r;
        
        //printf("Gen #%-10i Pop: %-10i Susceptible: %-10i Infected: %-10i Recovered: %-10i\n", gen, pop, s, i, r);
        printf("%i %i %i %i\n", pop, s, i, r);
        file << gen << " " << pop << " " << s << " " << i << " " << r << std::endl;
        gen++;
    }
    
    file.close();
    return 0;
}
