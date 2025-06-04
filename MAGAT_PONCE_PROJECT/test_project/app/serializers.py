# app/serializers.py
from rest_framework import serializers
from .models import tableSample

class TableSampleSerializer(serializers.ModelSerializer):
    class Meta:
        model = tableSample
        fields = ['name', 'age'] # These MUST match your tableSample model fields: