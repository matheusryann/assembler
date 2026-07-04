// Computes max(R0, R1) and stores the result in R2

@0
D=M
@1
D=D-M
@IFGT
D;JGT
@1
D=M
@OUTPUT
0;JMP
(IFGT)
@0
D=M
(OUTPUT)
@2
M=D
