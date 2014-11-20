START_COMMAND="start"
STOP_COMMAND="stop"

if [ "$1" = "$START_COMMAND" ] ; then
	echo "Starting employee web application...."
	mvn jetty:run 
	exit 0
	
fi

if [ "$1" = "$STOP_COMMAND" ] ; then
	PID=`fuser 9002/tcp`
	echo "$PID"
	if [ $PID ] ; then
		echo "Shutting down the employee web application."
		kill -9 $PID
	else
		echo "The employee web application is not running"
	fi
fi
