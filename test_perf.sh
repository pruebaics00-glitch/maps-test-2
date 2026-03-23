echo "Testing DB query..."
curl -o /dev/null -s -w "%{time_total}\n" http://localhost:8000/api/pois
