import sys
import pickle
import pandas as pd
from sklearn.preprocessing import LabelEncoder

# Récupérer les arguments passés depuis Java
experience = float(sys.argv[1])
service = sys.argv[2]  # Ex: INFORMATIQUE
role = sys.argv[3]     # Ex: Magasinier

# Charger le modèle
with open('modele_salaire.pkl', 'rb') as f:
    model = pickle.load(f)

# Recréer les label encoders avec les mêmes classes qu'à l'entraînement
label_encoder_service = LabelEncoder()
label_encoder_role = LabelEncoder()

# Doivent contenir toutes les valeurs EXACTES des enums Java utilisées lors de l'entraînement
label_encoder_service.fit([
    'RESSOURCES_HUMAINES',
    'INFORMATIQUE',
    'FINANCES',
    'SECRETARIAT',
    'LOGISTIQUE',
    'AUTRE'
])

label_encoder_role.fit([
    'Administrateur',
    'Magasinier',
    'Employe'
])

# Encoder les valeurs entrées
try:
    service_encoded = label_encoder_service.transform([service])[0]
    role_encoded = label_encoder_role.transform([role])[0]
except ValueError as e:
    print(f"Erreur de saisie : {e}")
    sys.exit(1)

# Créer le DataFrame
data = pd.DataFrame([{
    'experience': experience,
    'service': service_encoded,
    'role': role_encoded
}])

# Faire la prédiction
prediction = model.predict(data)

# Afficher la prédiction (sera capturée par Java)
print(f"{prediction[0]}")
