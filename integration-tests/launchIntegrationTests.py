import re
import os
import sys
import subprocess

print("Running integration tests")

path = "integration-tests"
for f in os.listdir(path):
    if (re.match('^test(.)+\.py$', f)):
        command = "python3 " + path + "/" + f
        p = subprocess.Popen(command, stdout=subprocess.PIPE, shell=True)
        print("Running " + command)
        status = p.wait()

        if (status != 0):
            print ("[FAILURE] File " + f + " has some failing unit tests")
            sys.exit(1)
        else:
            print ("[SUCCESS]")

