import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import LabelEncoder
from sklearn.metrics import mean_squared_error
import pickle

# Lire le fichier CSV
data = pd.read_csv('data/salaires.csv')

# Convertir les données catégorielles en numériques avec LabelEncoder
label_encoder_service = LabelEncoder()
label_encoder_role = LabelEncoder()

data['service'] = label_encoder_service.fit_transform(data['service'])
data['role'] = label_encoder_role.fit_transform(data['role'])

# Séparer les données en variables d'entrée (X) et variable de sortie (y)
X = data[['experience', 'service', 'role']]  # Entrées
y = data['salaire']  # Sortie (salaire)

# Séparer en données d'entraînement et de test
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Créer et entraîner le modèle
model = LinearRegression()
model.fit(X_train, y_train)

# Faire des prédictions
y_pred = model.predict(X_test)

# Évaluer le modèle
mse = mean_squared_error(y_test, y_pred)
print(f"Erreur quadratique moyenne (MSE) : {mse}")

# Sauvegarder le modèle dans un fichier
with open('modele_salaire.pkl', 'wb') as f:
    pickle.dump(model, f)

print("Le modèle a été entraîné et sauvegardé sous 'modele_salaire.pkl'.")
