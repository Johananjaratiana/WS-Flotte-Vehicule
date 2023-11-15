curl --request GET \
  --url https://ws-flotte-vehicule-production.up.railway.app/api/vehicules \
  --header 'Authorization: Bearer c439d4ad-7e52-445e-ac41-0025417914c3' \
  --header 'Content-Type: application/json' \
  --data '{"query":"query { me { name email } }"}'