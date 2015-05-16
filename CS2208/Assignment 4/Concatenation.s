		AREA Concatenation, CODE, READONLY
		ENTRY

eos		EQU 0x00						; Define end of character (EoS) for comparison use
		ADR	r1, STRING1					; Point at string 1 to be concatenated
		ADR	r2, STRING2					; Point at string 2 to be concatenated
		ADR	r3, STRING3					; Point at string 3 to hold the resulting concatenation
		
										; REPEAT
first	LDRB r4, [r1], #1				; Load a byte of string 1, then point to next byte
		CMP	r4, #eos					; Check if byte is EoS character
		STRBNE r4, [r3], #1				; If not, append byte to string 3, then point to next string 3 byte
		BNE	first						; UNTIL EoS is reached
										
										; REPEAT
second	LDRB r4, [r2], #1				; Load a byte of string 2, then point to next byte
		STRB r4, [r3], #1				; Append byte to string 3, then point to next string 3 byte
		CMP	r4, #eos					; Check if last byte to be appended was EoS character
		BNE	second						; UNTIL EoS is appended to concatenation string
		
loop	B loop							; End program with infinite loop
		
STRING1 DCB "This is a test string1" 	; String1
EoS		DCB 0x00 						; End of string1/null character
STRING2 DCB "This is a test string2" 	; String2
EoS2	DCB 0x00 						; End of string2/null character
STRING3 SPACE 0xFF						; Result of concatenation

		END
			