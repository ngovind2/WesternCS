		AREA RemoveWord, CODE, READONLY
		ENTRY

null	EQU 0x00								; Define null constant for comparison use
t		EQU 0x74								; Define 't' for comparison use
spce 	EQU 0x20								; Define ' ' (space) for comparison use
byte	EQU 8									; Define # of bits in 1 byte

		ADR	r1, STRING1							; Point at string 1
		ADR	r2, STRING2							; Point at string 2
		LDR	r3, =0x74686520						; Store "the_" for comparison use (*literal is too large to be defined as above*)
		
												; SPECIAL CASE - beginning of sentence
		LDRB r5, [r1]							; Load first character of string 1
		CMP	r5, #t								; Is character a 't'?
		BEQ	check								; If so, branch to check if "he_" or "he\0" follows (if not, proceed)

												; REPEAT
loop	LDRB r5, [r1], #1						; Load a character of string 1
		STRB r5, [r2], #1						; Store the character
												; SPECIAL CASE - end of sentence
		CMP	r5, #null							; Check if it's the null character (end of string)
		BEQ	inf									; If so, terminate program
		CMP	r5, #spce							; Otherwise, check if it's a space. If so, "the_" or "the\0" may follow				
		BNE	loop								; UNTIL character is a space	
										
												; We are either at the sentence's beginning or the character is a space
check	MOV	r6, r1								; Save current position of string 1 pointer, in case we need to backtrack
		MOV	r4, #0								; Reset r4, which will point at a substring of string 1's next 4 characters
build	LDRB r5, [r1], #1						; Build substring - add one character at a time
		CMP	r5, #null							; Check if character is null character/at end of string
		LDREQ r3, =0x74686500					; If null & substring equals "the\0", it will be removed  (*literal is too large to be defined as above*)
		ADD	r4, r5								; Append character to substring
		CMP	r4, #0x10000000						; Check if the substring contains less than 4 characters (*literal is too large to be defined as above*)
		LSLLT r4, #byte							; If so, shift the substring 1 byte left
		BLT	build								; And continue building substring (otherwise, substring is fully built)
										
		CMP	r4, r3								; Check if substring is "the_" or "the\0" (if equal, substring must be removed)
		SUBEQ r1, #1							; If equal, decrement string 1's pointer to preserve space or null character 
		MOVNE r1, r6							; Otherwise, substring should not be removed so return string 1's pointer to initial position

		B loop									; Repeat character processing until string 1's null character is reached
		
inf		B		inf								; End program with terminating loop

STRING1 DCB "and the man said they must go" 	; String 1 
EoS		DCB 0x00 								; End of string 1
STRING2 SPACE 0xFF								; The resultant string
		
		ALIGN
		END
			