import sys
import threading

def worker(f,t):

    from firebase import firebase

    file = open("C:\Users\Robert\Downloads\Rat_Sightings.csv","r")
    lines = file.readlines()

    url = 'https://bhoratsdb.firebaseio.com/'
    firebase = firebase.FirebaseApplication(url, None)
    if(f>len(lines)):
        return
    if(t>len(lines)):
        t=len(lines)
    for line in lines[f:t]:
        if(line):
            line = line.split(",")
            data = {"borough":line[23],"city":line[16],"created":line[1],"incident_address":line[9],"incident_zip":line[8],"lat":line[49],"location_type":line[7],"lon":line[50]}
            result = firebase.patch('/RatSightingsTEST/'+str(line[0]),data)
    file.close()
    return



def main(argv):
    threads = []
    for i in range(10):
        t = threading.Thread(target=worker,args=(1691+10000*i,1691+10000*(i+1))
        #t = threading.Thread(target=worker,args=(i,i+1)) testing line
        threads.append(t)
        t.start()

if __name__ == '__main__':
  main(sys.argv)