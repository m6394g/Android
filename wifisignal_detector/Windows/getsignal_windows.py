import os

os.system("netsh wlan show networks mode=bssid > tempResult.txt")

foo=open("tempResult.txt",'r')

string = filter(None,foo.read().split('\n'))
SSID=[]
Signal=[]

for i in string:
	#print(i)
	#print("\n\n")
	if i.find('SSID')!=-1:
		SSID.append(i)
	elif i.find('Signal')!=-1:
		Signal.append(i)
    
toWrite=open("result.txt",'w')

for i in range(0,len(Signal)):
	toWrite.write(SSID[i*2])
	toWrite.write("      ")
	
	toWrite.write(Signal[i])
	
	toWrite.write("\n")




