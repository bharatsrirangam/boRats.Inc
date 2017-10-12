
file = open("C:\Users\Robert\Downloads\Rat_Sightings.csv","r")
file_json = open("C:\Users\Robert\Downloads\Rat_Sightings.json","w")
lines = file.readlines()
import json
data = {}
for line in lines[1:]:
    if(line):
        line = line.split(",")
        data[line[0]] = {"key":line[0],"borough":line[23],"city":line[16],"created":line[1],"incident_address":line[9],"incident_zip":line[8],"lat":line[49],"location_type":line[7],"lon":line[50]}
file_json.write(json.dumps({"RatSightings":data}))
file.close()


