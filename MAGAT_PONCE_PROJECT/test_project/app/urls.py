from django.urls import path
from . import views
from .views import TableSampleCreateAPIView

urlpatterns = [
    path('', views.home, name='home'),
    path('profile/', views.profile, name='profile'),
    path('api/tablesample/', TableSampleCreateAPIView.as_view(), name='tablesample_create_api'),
]