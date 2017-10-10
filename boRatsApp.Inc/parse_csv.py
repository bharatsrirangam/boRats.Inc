import sys

def main(argv):
    from firebase import firebase

    file = open("C:\Users\Robert\Downloads\Rat_Sightings.csv","r")
    lines = file.readlines()

    url = 'https://bhoratsdb.firebaseio.com/'
    firebase = firebase.FirebaseApplication(url, None)
    for line in lines[1:2]:
        if(line):
            line = line.split(",")

            data = {"borough":line[23],"city":line[16],"created":line[1],"incident_address":line[9],"incident_zip":line[8],"lat":line[49],"location_type":line[7],"lon":line[50]}
            result = firebase.patch('/RatSightings/'+str(line[0]),data)
            print result
    file.close()

if __name__ == '__main__':
  main(sys.argv)