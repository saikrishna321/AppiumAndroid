#!/usr/bin/env bash
echo 'Running Appium server'

export TERM=${TERM:-dumb}

clear
#Random Port generation

project_path=$(PWD)
rm -rf $project_path/reports
mkdir reports

echo "*********Stopping all appium-servers********"
killall -9 node
sleep 1
delete_ports=$(basename appium_ports)
delete_chrome_ports=$(basename chrome_ports)
delete_boot_ports=$(basename bootstrap_ports)
delete_device_id=$(basename device_id)
delete_device_name=$(basename device_name)
delete_device_os=$(basename device_os)

rm -f $delete_ports
rm -f $delete_chrome_ports
rm -f $delete_boot_ports
rm -f $delete_device_id
rm -f $delete_device_name
rm -f $delete_device_os

shift
adb devices|tail -n +2|cut -sf 1 > devices_updated; 

while read line           
do  
	adb -s $line shell getprop ro.build.version.release | sed -e 's/ //g' >> device_os
done < devices_updated

while read line           
do  
	adb -s $line shell getprop ro.product.model | sed -e 's/ //g' >> device_name 
done < devices_updated


echo '***********'
recordcount=$(wc -l < devices_updated)
echo "Total devices connected ----"$recordcount
echo "Generated appium ports"
for i in $(seq 1 $recordcount)
do 
		ports=$(jot -r 1  62000 63000)
		echo $ports >> appium_ports
		echo $ports
done

echo "Generated chromerdriver ports"
for i in $(seq 1 $recordcount) 
do 
		ports=$(jot -r 1  59000 61000)
		echo $ports >> chrome_ports
done

echo "Generated bootstrap ports"
for i in $(seq 1 $recordcount) 
do 
		ports=$(jot -r 1  55000 58000)
		echo $ports >> bootstrap_ports
done


echo "********Starting Appium on different ports********"
echo $'\360\237\215\272'   $'\360\237\215\272'    $'\360\237\215\272'   $'\360\237\215\272'    $'\360\237\215\272'
					echo "Hang on appium is brewing test scripts"
echo $'\360\237\215\272'   $'\360\237\215\272'     $'\360\237\215\272'  $'\360\237\215\272'    $'\360\237\215\272'


parallel --no-notice --xapply -a appium_ports -a devices_updated -a bootstrap_ports echo appium --session-override -p {1} -U {2} -bp {3}

parallel --no-notice --xapply -a appium_ports -a devices_updated -a bootstrap_ports appium --session-override -p {1} -U {2} -bp {3} >/dev/null 2>&1 &

echo "Starting Appium Test in parallel"
parallel --no-notice --xapply -a appium_ports -a devices_updated -a device_os -a device_name  mvn clean test -Denv.PORT={1} -Denv.DEVICE={2} -Denv.DEVICENAME={4} -Denv.REPORT=reports




