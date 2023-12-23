import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Table from '../../components/Table';

const ListCreditInstallments = () => {
  let { creditId } = useParams();

  const [installments, setInstallments] = useState(null);

  useEffect(() => {
    listCreditInstallments(creditId);
  }, [creditId]);

  const listCreditInstallments = async creditId => {
    const response = await fetch(
      `http://localhost:8080/installments/list/${creditId}`,
    );
    const installments = await response.json();
    setInstallments(installments);
  };

  const handlePayClick = item => {
    console.log(item);
  };

  const columns = [
    {
      title: 'Id',
      field: 'id',
    },
    {
      title: 'Due Date',
      field: 'dueDate',
    },
    {
      title: 'Remaining Amount',
      field: 'remainingAmount',
    },
    {
      title: 'Total Amount',
      field: 'totalAmount',
    },
    {
      title: 'Status',
      field: 'status',
    },
    {
      title: 'Actions',
      field: item => (
        <button
          type='button'
          className='btn btn-primary'
          onClick={() => handlePayClick(item)}
        >
          Pay
        </button>
      ),
    },
  ];

  return <Table columns={columns} data={installments} />;
};

export default ListCreditInstallments;
