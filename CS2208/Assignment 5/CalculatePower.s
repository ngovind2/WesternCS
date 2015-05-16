		AREA CalculatePower, CODE, READONLY

x		EQU 2						; Set value of base x
n		EQU 2						; Set value of exponent n
up		EQU 4						; Set stack increment value
	
		ENTRY						; MAIN
		ADR	sp, Stack				; Define stack
		MOV	r4, #n        			; Get value of exponent n (parameter)
		MOV	r3, #x        			; Get value of base x 
		
		STR	r4,[sp,#-up]!			; Push exponent n (parameter) on full descending stack
		SUB sp,sp,#up      			; Reserve stack space to hold return value
		
		BL Power					; Calculate power of x
		LDR r0,[sp],#up	    		; Load result into r0 and pop it from stack
		ADD sp,sp,#up     			; Pop corresponding parameter from stack

		ADR r1,Result     			; Get address of result variable
		STR r0,[r1]       			; Store final answer in result variable

Loop 	B Loop        				; Terminate program via infinite loop					
;-------------------------------------------------------------------------------------------------
		AREA CalculatePower, CODE, READONLY
			
Power 	STMFD sp!,{r0,r1,r2,fp,lr} 	; Push registers onto stack (general, frame pointer, link)
		MOV fp,sp         			; Set frame pointer for function call

		LDR r0,[fp,#0x18] 			; Get parameter from stack

Base	CMP r0,#0        			; Base case - when exponent n is 0
		MOVEQ r0,#1        			; If base case, set return value to 1
		STREQ r0,[fp,#0x14]  		; And push return value on stack
		BLEQ Return  	     		; And branch to return section
					
		AND r1,r0,#1				; If not base case, check if exponent is odd or even
		CMP r1,#1					; If odd, 
		BEQ	Odd						; Branch to Odd
		BNE	Even					; Otherwise, branch to Even
			
Odd		SUB r0,#1					; Subtract 1 from exponent n
		STR r0,[sp,#-up]!			; Push new exponent n on stack
		SUB sp,#up					; Reserve space for return value 
		
		BL Power					; Recursive call to Power function with new n value
		LDR r0,[sp],#up				; Load result value into r0 before popping it from stack
		ADD sp,#up					; Pop exponent (parameter) value
		MUL r1,r0,r3				; Calculate the result value
		STR r1,[fp,#0x14]			; Push value onto stack
		BL Return					; Branch to return section

Even	LSR r0,r0,#1				; Divide exponent by 2
		STR r0,[sp,#up]!			; Push new exponent n on stack
		SUB sp,#up					; Reserve space for return value
		
		BL Power					; Recursive call to Power function with new n value
		LDR r0,[sp],#up				; Load result value into r0 before popping it from stack
		ADD sp,#up					; Increment stack pointer
		MOV r1,r0					; Copy result value
		MUL r2,r1,r0				; Calculate square of Power's return value & store in r2
		STR r2,[fp,#0x14]			; Push value onto stack
		BL Return					; Branch to return section

Return 	MOV sp,fp         			; Collapse working spaces for function call
		LDMFD sp!,{r0,r1,r2,fp,pc} 	; Restore/reload initial settings of registers; return to caller
;-------------------------------------------------------------------------------------------------
		AREA CalculatePower, DATA, READWRITE
			
Result 	DCD   0x00					; To hold result of power of x calculation
		SPACE 0xB4       			; Reserve memory space for stack
Stack	DCD   0x00        			; Initial position of full descending stack
;-------------------------------------------------------------------------------------------------
		END