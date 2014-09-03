import os
os.system("sudo iwlist wlan0 scan | grep 'ESSID\|Signal' | tr -s ' ' | cut -d' ' -f2- > tempResult")

foo = open("tempResult", "r")


string = foo.read().split('\n')

Quality,ESSID = filter(None,string[::2]),filter(None,string[1::2])
#print Quality

#print ESSID


toWrite = open('result.txt',"w")
for i in xrange(0,len(ESSID)):
	toWrite.write(ESSID[i])
	toWrite.write("      ")
	toWrite.write(Quality[i])
	toWrite.write("\n")

os.system("rm tempResult")
