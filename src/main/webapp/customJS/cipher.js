function AES_Decode(base64_text)
{
	GibberishAES.size(128);	
	return GibberishAES.aesDecrypt(base64_text, "abcdefghijklmnopabcdefghijklmnop");
}

function AES_Encode(plain_text)
{
	GibberishAES.size(128);	
	return GibberishAES.aesEncrypt(plain_text, "abcdefghijklmnop");
}