		AREA Polynomial, CODE, READWRITE
		ENTRY
								; MAIN PROGRAM
x		EQU 3					; Choose value to be computed on
		MOV	r0, #x				; Store value in r0
		BL	subrt				; Call subroutine to determine polynomial output
		
		LSL r0, #1				; Double result of subroutine & store in r0
loop	B loop					; End program with infinite loop

								; SUBROUTINE
subrt	STMFD sp!,{r1-r6}		; Save settings of registers 1-6

		ADR	r1, a				; Point to coefficient a of polynomial
		ADR	r2, b				; Point to coefficient b of polynomial
		ADR	r3, c				; Point to coefficient c of polynomial
		ADR	r4, clip			; Point to clipping limit
		
		LDR	r5, [r2] 			; Load value of coefficient b
		LDR	r6, [r3]			; Load value of coefficient c
		MLA	r2, r5, r0, r6		; Store result of b*x + c
		
		MOV r5, r0				; Load a copy of [r0] value (x)
		MOV	r6, r0				; Load second copy of [r0] value (x)
		MUL	r0, r5, r6  		; Compute square of [r0] (x^2)
		LDR r5, [r1]			; Load value of coefficient a
		MUL	r1, r5, r0			; Store result of a*x^2
		
		ADD	r0, r1, r2			; Sum ax^2 + bx + c
		
		LDR r1, [r4]			; Load value of clipping limit
		CMP r0, r1				; Compare output to clipping limit
		MOVGT r0, r1			; Replace output with limit if output > limit
		
		LDMFD sp!,{r1-r6}		; Restore register settings
		BX lr					; Return to main function

a		DCD	5					; Coefficient a of polynomial (ax^2)
b		DCD	6					; Coefficient b of polynomial (bx)
c		DCD	7					; Coefficient c of polynomial (c)
clip	DCD	90					; Clipping limit (output cannot exceed this value)
	
		END