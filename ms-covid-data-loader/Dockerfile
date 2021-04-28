FROM python:3.9

COPY requirements.txt .

RUN pip3 install --user -r requirements.txt

COPY *.py ./

CMD [ "python3", "loader.py" ]
