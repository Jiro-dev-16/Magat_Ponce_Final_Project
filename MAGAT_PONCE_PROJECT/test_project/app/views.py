from django.shortcuts import render, redirect
from django.contrib import messages # Import the messages framework
from django.http import HttpResponse # Keep if you want to return simple text responses
from rest_framework import status, generics
from rest_framework.response import Response
from .models import tableSample
from .serializers import TableSampleSerializer
from rest_framework import generics
# app/views.py

def home(request):
    if request.method == 'POST':
        name = request.POST.get('name')
        age = request.POST.get('age')

        if not name:
            messages.error(request, "Full Name is required.")
        if not age:
            messages.error(request, "Age is required.")

        # Osnly proceed if both fields are valid
        if name and age:
            try:
                # Convert age to int and add more robust validation if needed
                age_int = int(age)
                if not (1 <= age_int <= 120): # Ensure age is within a reasonable range
                    messages.error(request, "Age must be between 1 and 120.")
                    return render(request, 'home.html')

                new_entry = tableSample(name=name, age=age_int)
                new_entry.save()
                messages.success(request, "Profile saved successfully!")
                return redirect('home')
            except ValueError:
                messages.error(request, "Invalid age format. Please enter a number.")
            except Exception as e:
                messages.error(request, f"Error saving data: {e}")
        
        # If there are errors or missing fields, re-render the home page
        return render(request, 'home.html')
    else:
        # For GET requests, just render the form
        return render(request, 'home.html')

def profile(request):
    """
    Renders a simple profile page. In a real application, this would
    likely display user-specific data.
    """
    return HttpResponse("Welcome to the Profile Page!")

# --- API View for tableSample ---
class TableSampleCreateAPIView(generics.CreateAPIView):
    queryset = tableSample.objects.all()
    serializer_class = TableSampleSerializer