import os
import sys
import argparse
import subprocess
import logging

def jarWrapper(*args):
    javaExec = os.path.normpath(os.path.join(os.getenv('JAVA_HOME'),'bin','java'))
    if not (os.path.isfile(javaExec)): 
       raise Exception('java runtime cannot be found', javaExec) 
    javaArgs=[javaExec, '-cp']+list(args)
    logging.debug('javaArgs=' + ' '.join(javaArgs))
    process = subprocess.Popen(javaArgs, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    lines = []
    while process.poll() is None:
        line = process.stdout.readline()
        if line != '' and line.endswith('\n'):
            lines.append(line[:-1])
    stdout, stderr = process.communicate()
    lines += stdout.split('\n')
    if stderr != '':
        lines += stderr.split('\n')
    lines.remove('')
    for line in lines:
       logging.debug('javaOut: ' + line)
    return process.returncode

def main_args():
    parser = argparse.ArgumentParser()
    optional = parser._action_groups.pop()
    required = parser.add_argument_group('required arguments')
    required.add_argument('--p1', type=int, required=True, help='usage of parameter #1')
    optional.add_argument('--p2', help='usage of parameter #2')
    parser._action_groups.append(optional)
    args = parser.parse_args()
    return args

def main(args):
    jars = []
    for file in os.listdir('lib'):
        if file.endswith('.jar'):
            jars.append(os.path.join('lib', str(file)))
    cp = str(os.pathsep).join(jars)
    args = [cp,'com.balionis.docker2.MyApp', str(args.p1), args.p2]
    ret = jarWrapper(*args)
    logging.info('done with ' + str(ret))
    return ret    

#logFilename=os.path.join(os.path.basename(__file__),'.log')
#logging.basicConfig(filename=logFilename,level=logging.DEBUG)
logging.basicConfig(level=logging.DEBUG)

args = main_args()
ret  = main(args)

sys.exit(ret)

