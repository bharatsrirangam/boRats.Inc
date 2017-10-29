
file = open("C:\Users\Robert\Downloads\Rat_Sightings.csv","r")
file_json = open("C:\Users\Robert\Downloads\Rat_Sightings.json","w")
lines = file.readlines()
import json
import re
data = {}
for line in lines[1:]:
    if(line):
        line = line.split(",")
        address = line[1]
        address=re.split('/| |:',address)
        address = address[2]+address[0]+address[1]+address[3]+address[4]+address[5]
        address = int(address)
        data[line[0]] = {"key":line[0],"borough":line[23],"city":line[16],"created":line[1],"incident_address":line[9],"incident_zip":line[8],"lat":line[49],"location_type":line[7],"lon":line[50],"date_num":address}
file_json.write(json.dumps({"RatSightings":data}))
file.close()


