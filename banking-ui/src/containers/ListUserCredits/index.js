import React, { useEffect, useState } from 'react';
import Table from '../../components/Table';
import { useNavigate, useParams } from 'react-router-dom';

const ListUserCredits = () => {
  const navigate = useNavigate();

  const [credits, setCredits] = useState(null);
  let { id } = useParams();

  useEffect(() => {
    listCredits(id);
  }, [id]);

  const listCredits = async id => {
    const response = await fetch(`http://localhost:8080/credits/list/${id}`);
    const credits = await response.json();
    setCredits(credits);
  };

  const handleInstallmentsClick = item => {
    navigate(`/listCreditInstallments/${item.id}`);
  };

  const columns = [
    {
      title: 'Id',
      field: 'id',
    },
    {
      title: 'Status',
      field: 'status',
    },
    {
      title: 'Amount',
      field: 'amount',
    },
    {
      title: 'Installment Count',
      field: 'installmentCount',
    },
    {
      title: 'Actions',
      field: item => (
        <button
          type='button'
          className='btn btn-primary'
          onClick={() => handleInstallmentsClick(item)}
        >
          Show Installments
        </button>
      ),
    },
  ];

  return <Table columns={columns} data={credits} />;
};

export default ListUserCredits;
